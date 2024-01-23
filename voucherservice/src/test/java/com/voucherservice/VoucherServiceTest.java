package com.voucherservice;



 
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.voucherservice.entity.Voucher;
import com.voucherservice.exception.NoVoucherPresentException;
import com.voucherservice.repository.VoucherRepository;
import com.voucherservice.service.VoucherServiceImpl;
 
 class VoucherServiceTest {
 
    @Mock
    private VoucherRepository voucherRepo;
 
    @InjectMocks
    private VoucherServiceImpl voucherService;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
 
//    @Test
//    public void testSaveAllVouchers() throws IOException, DataIsNotInsertedException {
//        // Mock data
//        MultipartFile mockFile = mock(MultipartFile.class);
//        List<Voucher> mockVouchers = new ArrayList<>();
//        when(Excelhelper.convertExcelToListOfVoucher(any())).thenReturn(mockVouchers);
//        when(voucherRepo.saveAll(any())).thenReturn(mockVouchers);
//
//        // Test the method
//        List<Voucher> result = voucherService.saveAllVouchers(mockFile);
//
//        // Verify
//        verify(voucherRepo, times(1)).saveAll(mockVouchers);
//        Assertions.assertEquals(mockVouchers, result);
//    }
 
    @Test
     void testGetAllVouchers() throws NoVoucherPresentException {
        // Mock data
        List<Voucher> mockVouchers = new ArrayList<>();
        when(voucherRepo.findAll()).thenReturn(mockVouchers);
 
        // Test the method
        Assertions.assertThrows(NoVoucherPresentException.class, () -> voucherService.getAllVouchers());
 
        // Verify
        verify(voucherRepo, times(1)).findAll();
    }
 
    @Test
     void testGetAllVoucherByExamName() throws NoVoucherPresentException {
        // Mock data
        String examName = "MockExam";
        List<Voucher> mockVouchers = new ArrayList<>();
        when(voucherRepo.findByExamName(examName)).thenReturn(mockVouchers);
 
        // Test the method
        Assertions.assertThrows(NoVoucherPresentException.class, () -> voucherService.getAllVoucherByExamName(examName));
 
        // Verify
        verify(voucherRepo, times(1)).findByExamName(examName);
    }
 
    @Test
    void testGetAllVoucherByCloudPlatform() throws NoVoucherPresentException {
        // Mock data
        String cloudPlatform = "MockPlatform";
        List<Voucher> mockVouchers = new ArrayList<>();
        when(voucherRepo.findByCloudPlatform(cloudPlatform)).thenReturn(mockVouchers);
 
        // Test the method
        Assertions.assertThrows(NoVoucherPresentException.class, () -> voucherService.getAllVoucherByCloudPlatform(cloudPlatform));
 
        // Verify
        verify(voucherRepo, times(1)).findByCloudPlatform(cloudPlatform);
    }
 
    @Test
   void testGetAllExpiredVoucher() throws NoVoucherPresentException {
        // Mock data
        List<Voucher> mockVouchers = new ArrayList<>();
        when(voucherRepo.findAll()).thenReturn(mockVouchers);
 
        // Test the method
        Assertions.assertThrows(NoVoucherPresentException.class, () -> voucherService.getAllExpiredVoucher());
 
        // Verify
        verify(voucherRepo, times(1)).findAll();
    }
 
    @Test
     void testGetVoucherById() throws NoVoucherPresentException {
        // Mock data
        String voucherId = "mockId";
        Optional<Voucher> mockOptional = Optional.of(new Voucher());
        when(voucherRepo.findById(voucherId)).thenReturn(mockOptional);
 
        // Test the method
        Voucher result = voucherService.getVoucherById(voucherId);
 
        // Verify
        verify(voucherRepo, times(1)).findById(voucherId);
        Assertions.assertEquals(mockOptional.get(), result);
    }
 
//    @Test
//    public void testAssignUserEmail() throws NoVoucherPresentException {
//        // Mock data
//        String voucherId = "mockId";
//        String userEmail = "mock@example.com";
//        Optional<Voucher> mockOptional = Optional.of(new Voucher());
//        when(voucherRepo.findById(voucherId)).thenReturn(mockOptional);
//        when(voucherRepo.save(any())).thenReturn(new Voucher());
//
//        // Test the method
//        Voucher result = voucherService.assignUserEmail(voucherId, userEmail);
//
//        // Verify
//        verify(voucherRepo, times(1)).findById(voucherId);
//        verify(voucherRepo, times(1)).save(any());
//        Assertions.assertEquals(userEmail, result.getIssuedTo());
//    }
 
    @Test
     void testRemoveVoucherById() throws NoVoucherPresentException {
        // Mock data
        String voucherId = "mockId";
        Optional<Voucher> mockOptional = Optional.of(new Voucher());
        when(voucherRepo.findById(voucherId)).thenReturn(mockOptional);
        doNothing().when(voucherRepo).deleteById(voucherId);
 
        // Test the method
        String result = voucherService.removeVoucherById(voucherId);
 
        // Verify
        verify(voucherRepo, times(1)).findById(voucherId);
        verify(voucherRepo, times(1)).deleteById(voucherId);
        Assertions.assertEquals("Voucher Deleted Successfully", result);
    }
}
 
