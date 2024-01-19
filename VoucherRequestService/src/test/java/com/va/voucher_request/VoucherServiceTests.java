package com.va.voucher_request;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.va.voucher_request.exceptions.NotFoundException;
import com.va.voucher_request.exceptions.ScoreNotValidException;
import com.va.voucher_request.model.VoucherRequest;
import com.va.voucher_request.model.VoucherRequestDto;
import com.va.voucher_request.repo.VoucherRequestRepository;
import com.va.voucher_request.service.VoucherReqService;
import com.va.voucher_request.service.VoucherReqServiceImpl;
 
import java.time.LocalDate;
import java.util.Collections;
 
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;
 
@SpringBootTest
class VoucherServiceTests {
 
    @Mock
    private VoucherRequestRepository voucherRepository;
 
    @InjectMocks
    private VoucherReqServiceImpl voucherService;
 
 
    @Test
    void testRequestVoucherWithInvalidScore() {
        VoucherRequestDto requestDto = new VoucherRequestDto();
        requestDto.setDoSelectScore(75);
        assertThrows(ScoreNotValidException.class, () -> voucherService.requestVoucher(requestDto));
        verify(voucherRepository, never()).save(any(VoucherRequest.class));
    }
 
//    @Test
//    void testGetAllVouchersByCandidateEmail() throws NotFoundException {
//        // Given
//        VoucherRequestDto requestDto = new VoucherRequestDto();
//        requestDto.setDoSelectScore(85);
//
//        // Mock repository behavior
//        when(voucherRepository.findByCandidateEmail(requestDto.getCandidateEmail()))
//                .thenReturn(Collections.singletonList(new VoucherRequest()));
//
//        // When
//        List<VoucherRequestDto> result = voucherService.getAllVouchersByCandidateEmail(requestDto.getCandidateEmail());
//
//        // Then
//        assertFalse(result.isEmpty());
//        assertEquals(requestDto.getCandidateEmail(), result.get(0).getCandidateEmail());
//
//        // Verify that the repository's findByCandidateEmail method was called
//        verify(voucherRepository, times(1)).findByCandidateEmail(requestDto.getCandidateEmail());
//    }
 
    @Test
    void testGetAllVouchersByNonexistentCandidateEmail() {
        String nonexistentEmail = "nonexistent@example.com";
        when(voucherRepository.findByCandidateEmail(nonexistentEmail)).thenReturn(Collections.emptyList());
 
        assertThrows(NotFoundException.class, () -> voucherService.getAllVouchersByCandidateEmail(nonexistentEmail));
        verify(voucherRepository, times(1)).findByCandidateEmail(nonexistentEmail);
    }
    @Test
    void testUpdateExamDate() {
        String voucherCode = "ABC123";
        LocalDate newExamDate = LocalDate.now().plusDays(7);
 
        VoucherRequest existingVoucherRequest = new VoucherRequest();
        existingVoucherRequest.setVoucherCode(voucherCode);
 
        when(voucherRepository.findByVoucherCode(voucherCode)).thenReturn(existingVoucherRequest);
 
        VoucherRequest updatedVoucherRequest = null;
        try {
            updatedVoucherRequest = voucherService.updateExamDate(voucherCode, newExamDate);
        } catch (NotFoundException e) {
            fail("NotFoundException not expected for a valid voucher code");
        }
 
        verify(voucherRepository, times(1)).save(existingVoucherRequest);
        assertNotNull(updatedVoucherRequest);
        assertEquals(newExamDate, updatedVoucherRequest.getPlannedExamDate());
    }
 
    @Test
    void testUpdateExamResult() {
        String voucherCode = "ABC123";
        String newExamResult = "Pass";
 
        VoucherRequest existingVoucherRequest = new VoucherRequest();
        existingVoucherRequest.setVoucherCode(voucherCode);
 
        when(voucherRepository.findByVoucherCode(voucherCode)).thenReturn(existingVoucherRequest);
 
        VoucherRequest updatedVoucherRequest = null;
        try {
            updatedVoucherRequest = voucherService.updateExamResult(voucherCode, newExamResult);
        } catch (NotFoundException e) {
            fail("NotFoundException not expected for a valid voucher code");
        }
 
        verify(voucherRepository, times(1)).save(existingVoucherRequest);
        assertNotNull(updatedVoucherRequest);
        assertEquals(newExamResult, updatedVoucherRequest.getExamResult());
    }
 
}
