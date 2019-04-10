package org.oscm.rest.marketplace;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.marketplace.data.EntryRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/marketplaces" + CommonParams.PATH_ID + "/entries/{sKey}")
@Stateless
public class EntryResource extends RestResource {

  @EJB EntryBackend eb;

  @Since(CommonParams.VERSION_1)
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCatalogEntry(
      @Context UriInfo uriInfo,
      EntryRepresentation content,
      @BeanParam MarketplaceParameters params)
      throws Exception {
    return put(uriInfo, eb.put(), content, params);
  }
}