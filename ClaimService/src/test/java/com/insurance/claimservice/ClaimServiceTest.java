package com.insurance.claimservice;

import com.insurance.claimservice.feignClient.PolicyClient;
import com.insurance.claimservice.dto.PolicyDTO;
import com.insurance.claimservice.exception.ClaimException;
import com.insurance.claimservice.model.Claim;
import com.insurance.claimservice.repository.ClaimRepository;
import com.insurance.claimservice.service.ClaimServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClaimServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ClaimServiceImplTest.class);

    @InjectMocks
    private ClaimServiceImpl claimService;

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private PolicyClient policyClient;

    private Claim testClaim;
    private PolicyDTO testPolicy;

    private AutoCloseable closeable;

    @BeforeAll
    void setupAll() {
        logger.info("Starting ClaimServiceImpl unit tests...");
    }

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        testClaim = new Claim();
        testClaim.setClaimId(1L);
        testClaim.setPolicyId(1001L);
        testClaim.setCustomerId(501L);
        testClaim.setClaimAmount(1500.0);
        testClaim.setStatus("filed");

        testPolicy = new PolicyDTO();
        testPolicy.setPolicyId(1001L);
        testPolicy.setPremiumAmount(1200.0);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        logger.info("Cleaned up test context.");
    }

    @AfterAll
    void completeAll() {
        logger.info("All tests completed.");
    }

    @Test
    void testFileClaim() {
        when(claimRepository.save(testClaim)).thenReturn(testClaim);
        Claim saved = claimService.fileClaim(testClaim);

        assertThat(saved.getClaimId()).isEqualTo(1L);
        assertThat(saved.getStatus()).isEqualTo("filed");
        verify(claimRepository, times(1)).save(testClaim);
    }

    @Test
    void testUpdateClaim_Success() {
        Claim updatedInput = new Claim();
        updatedInput.setClaimAmount(1600.0);
        updatedInput.setStatus("under_review");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(testClaim));
        when(claimRepository.save(any(Claim.class))).thenReturn(testClaim);

        Claim updated = claimService.updateClaim(1L, updatedInput);

        assertThat(updated.getClaimAmount()).isEqualTo(1600.0);
        assertThat(updated.getStatus()).isEqualTo("under_review");
    }

    @Test
    void testUpdateClaim_NotFound() {
        when(claimRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> claimService.updateClaim(99L, new Claim()))
                .isInstanceOf(ClaimException.class)
                .hasMessageContaining("Claim not found with ID: 99");
    }

    @Test
    void testGetClaimById_Success() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(testClaim));

        Claim result = claimService.getClaimById(1L);
        assertThat(result.getPolicyId()).isEqualTo(1001L);
    }

    @Test
    void testGetClaimById_NotFound() {
        when(claimRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> claimService.getClaimById(2L))
                .isInstanceOf(ClaimException.class)
                .hasMessageContaining("Claim not found with ID: 2");
    }

    @Test
    void testGetAllClaims() {
        when(claimRepository.findAll()).thenReturn(List.of(testClaim));

        List<Claim> allClaims = claimService.getAllClaims();
        assertThat(allClaims).isNotEmpty().hasSize(1);
    }

    @Test
    void testDeleteClaim_Success() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(testClaim));
        doNothing().when(claimRepository).deleteById(1L);

        String message = claimService.deleteClaim(1L);
        assertThat(message).isEqualTo("Claim deleted with ID: 1");
    }

    @Test
    void testDeleteClaim_NotFound() {
        when(claimRepository.findById(10L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> claimService.deleteClaim(10L))
                .isInstanceOf(ClaimException.class)
                .hasMessageContaining("Claim not found with ID: 10");
    }

    @Test
    void testTrackClaimStatus_Approved() {
        testClaim.setClaimAmount(1500.0); // Higher than premium
        testPolicy.setPremiumAmount(1200.0);

        when(claimRepository.findById(1L)).thenReturn(Optional.of(testClaim));
        when(policyClient.getPolicyById(testClaim.getPolicyId())).thenReturn(testPolicy);
        when(claimRepository.save(any(Claim.class))).thenReturn(testClaim);

        String status = claimService.trackClaimStatus(1L);
        assertThat(status).isEqualTo("approved");
    }

    @Test
    void testTrackClaimStatus_Rejected() {
        testClaim.setClaimAmount(1000.0); // Lower than premium
        testPolicy.setPremiumAmount(1200.0);

        when(claimRepository.findById(1L)).thenReturn(Optional.of(testClaim));
        when(policyClient.getPolicyById(testClaim.getPolicyId())).thenReturn(testPolicy);
        when(claimRepository.save(any(Claim.class))).thenReturn(testClaim);

        String status = claimService.trackClaimStatus(1L);
        assertThat(status).isEqualTo("rejected");
    }
}