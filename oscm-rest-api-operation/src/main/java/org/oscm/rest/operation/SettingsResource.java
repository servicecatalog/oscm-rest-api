/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.operation.data.SettingRepresentation;

@Path(CommonParams.PATH_VERSION + "/settings")
@Stateless
public class SettingsResource extends RestResource {

  @EJB SettingsBackend sb;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSettings(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response getSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @Since(CommonParams.VERSION_1)
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response updateSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response deleteSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
