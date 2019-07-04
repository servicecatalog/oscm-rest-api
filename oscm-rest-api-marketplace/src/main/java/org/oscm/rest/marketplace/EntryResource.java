/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.marketplace;

import lombok.AccessLevel;
import lombok.Setter;
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

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EntryBackend eb;

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
