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
import org.oscm.rest.subscription.data.SubscriptionCreationRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/subscriptions")
@Stateless
public class SubscriptionResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SubscriptionBackend sb;

  @GET
  public Response getSubscriptions(
      @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  public Response getSubscription(
          @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  public Response createSubscription(
      @Context UriInfo uriInfo,
      SubscriptionCreationRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Path(CommonParams.PATH_ID)
  public Response updateSubscription(
      @Context UriInfo uriInfo,
      SubscriptionCreationRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  public Response deleteSubscription(
      @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
