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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.errorhandling.RestErrorResponseFactory;
import org.oscm.rest.common.representation.MarketplaceRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

@Path(CommonParams.PATH_VERSION + "/marketplaces")
@Stateless
public class MarketplaceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  MarketplaceBackend mb;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMarketplaces(@Context UriInfo uriInfo, @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return getCollection(uriInfo, mb.getCollection(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createMarketplace(
      @Context UriInfo uriInfo,
      MarketplaceRepresentation content,
      @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return post(uriInfo, mb.post(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response getMarketplace(@Context UriInfo uriInfo, @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return get(uriInfo, mb.get(), params, true);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @Since(CommonParams.VERSION_1)
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response updateMarketplace(
      @Context UriInfo uriInfo,
      MarketplaceRepresentation content,
      @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return put(uriInfo, mb.put(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response deleteMarketplace(
      @Context UriInfo uriInfo, @BeanParam MarketplaceParameters params) throws Exception {
    try {
      return delete(uriInfo, mb.delete(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }
}
