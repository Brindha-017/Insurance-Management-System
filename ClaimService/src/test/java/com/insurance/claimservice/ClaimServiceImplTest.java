package com.insurance.claimservice;
import com.insurance.claimservice.model.Claim; 
import com.insurance.claimservice.exception.ClaimException; 
import com.insurance.claimservice.repository.ClaimRepository; 
import com.insurance.claimservice.feignClient.PolicyClient; 
import com.insurance.claimservice.service.ClaimServiceImpl; 
import com.insurance.claimservice.dto.PolicyDTO; 
import org.junit.jupiter.api.*; 
import org.mockito.InjectMocks; 
import org.mockito.Mock; 
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ClaimServiceImplTest {
	@InjectMocks
	private ClaimServiceImpl claimService;

	@Mock
	private ClaimRepository claimRepository;

	@Mock
	private PolicyClient policyClient;

	private AutoCloseable closeable;

	private Claim claim;

	@BeforeAll
	static void setupBeforeAll() {
	    System.out.println("Initializing ClaimServiceImplTest class...");
	}

	@BeforeEach
	void setUp() {
	    closeable = MockitoAnnotations.openMocks(this);
	    claim = new Claim();
	    claim.setClaimId(1L);
	    claim.setPolicyId(101L);
	    claim.setCustomerId(1001L);
	    claim.setClaimAmount(5000);
	    claim.setStatus("filed");
	}


	@Test
	void testGetClaimById() {
	    when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

	    Claim result = claimService.getClaimById(1L);
	    assertNotNull(result);
	    assertEquals(1L, result.getClaimId());
	}

	@Test
	void testGetClaimById_NotFound() {
	    when(claimRepository.findById(2L)).thenReturn(Optional.empty());

	    assertThrows(ClaimException.class, () -> {
	        claimService.getClaimById(2L);
	    });
	}

	@Test
	void testGetAllClaims() {
	    List<Claim> claims = Arrays.asList(claim);
	    when(claimRepository.findAll()).thenReturn(claims);

	    List<Claim> result = claimService.getAllClaims();
	    assertEquals(1, result.size());
	}


	@AfterEach
	void tearDown() throws Exception {
	    closeable.close();
	}

	@AfterAll
	static void tearDownAfterAll() {
	    System.out.println("Completed ClaimServiceImplTest class.");
	}
}