package org.oscm.rest.marketplace;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.marketplace.data.EntryRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntryResourceTest {

    @Mock
    private EntryBackend entryBackend;

    @InjectMocks
    @Spy
    private EntryResource entryResource;

    private Response response;
    private MarketplaceParameters marketplaceParameters;
    private UriInfo uriInfo;
    private EntryRepresentation entryRepresentation;

    @BeforeEach
    public void setUp() {
        entryRepresentation = new EntryRepresentation();
        uriInfo = SampleTestDataUtility.createUriInfo();
        marketplaceParameters = createParameters();
    }

    @AfterEach
    public void cleanUp() {
        response = null;
    }

    @Test
    public void shouldUpdateCatalogEntry() {
        try {
            when(entryBackend.put()).thenReturn((content, params) -> true);
        } catch (Exception e) {
            fail(e);
        }

        try {
            response = entryResource.updateCatalogEntry(uriInfo, entryRepresentation, marketplaceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
    }

    private MarketplaceParameters createParameters() {
        MarketplaceParameters parameters = new MarketplaceParameters();
        parameters.setId(100L);
        parameters.setVersion(100);
        parameters.validateETag();
        return parameters;
    }
}