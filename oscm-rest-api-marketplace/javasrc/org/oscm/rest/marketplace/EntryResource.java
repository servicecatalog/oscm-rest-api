package org.oscm.rest.marketplace;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.marketplace.data.EntryRepresentation;

import com.sun.jersey.api.core.InjectParam;

@Path(CommonParams.PATH_VERSION + "/marketplaces" + CommonParams.PATH_ID
        + "/entries/{sKey}")
@Stateless
public class EntryResource extends RestResource {

    @EJB
    EntryBackend eb;

    @Since(CommonParams.VERSION_1)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCatalogEntry(@Context Request request,
            EntryRepresentation content,
            @InjectParam MarketplaceParameters params) throws Exception {
        return put(request, eb.put(), content, params);
    }

}
