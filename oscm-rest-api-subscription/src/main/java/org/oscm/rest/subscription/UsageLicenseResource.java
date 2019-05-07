/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/subscriptions" + CommonParams.PATH_ID + "/usagelicenses")
@Stateless
public class UsageLicenseResource extends RestResource {

  @EJB UsageLicenseBackend ulb;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLicenses(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return getCollection(uriInfo, ulb.getCollection(), params);
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return post(uriInfo, ulb.post(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{licKey}")
  public Response updateLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return put(uriInfo, ulb.put(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{licKey}")
  public Response deleteLicense(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return delete(uriInfo, ulb.delete(), params);
  }
}
