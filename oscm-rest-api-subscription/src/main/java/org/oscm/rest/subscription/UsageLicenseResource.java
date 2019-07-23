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

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/subscriptions" + CommonParams.PATH_ID + "/usagelicenses")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsageLicenseResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UsageLicenseBackend ulb;

  @GET
  public Response getLicenses(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return getCollection(uriInfo, ulb.getCollection(), params);
  }

  @POST
  public Response createLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return post(uriInfo, ulb.post(), content, params);
  }

  @PUT
  @Path("/{licKey}")
  public Response updateLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return put(uriInfo, ulb.put(), content, params);
  }

  @DELETE
  @Path("/{licKey}")
  public Response deleteLicense(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return delete(uriInfo, ulb.delete(), params);
  }
}
