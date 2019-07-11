/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.representation.BillingContactRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.requestparameters.AccountParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/billingcontacts")
@Stateless
public class BillingContactResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBillingContacts(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return getCollection(uriInfo, ab.getBillingContactCollection(), params);
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response createBillingContact(
      @Context UriInfo uriInfo,
      BillingContactRepresentation content,
      @BeanParam AccountParameters params)
      throws Exception {
    return post(uriInfo, ab.postBillingContact(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response getBillingContact(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return get(uriInfo, ab.getBillingContact(), params, true);
  }

  @Since(CommonParams.VERSION_1)
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response updateBillingContact(
      @Context UriInfo uriInfo,
      BillingContactRepresentation content,
      @BeanParam AccountParameters params)
      throws Exception {
    // FIXME: Move investigate why the same command doesn't work from RestResource#128
    content.setId(params.getId());
    return put(uriInfo, ab.putBillingContact(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response deleteBillingContact(
      @Context UriInfo uriInfo, @BeanParam AccountParameters params) throws Exception {
    return delete(uriInfo, ab.deleteBillingContact(), params);
  }
}
