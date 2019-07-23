/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.ServiceRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID + "/compatibleservices")
@Stateless
public class CompatibleServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;


  @GET
  public Response getCompatibleServices(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return getCollection(uriInfo, sb.getCompatibles(), params);
  }

  @PUT
  public Response setCompatibleServices(
      @Context UriInfo uriInfo,
      RepresentationCollection<ServiceRepresentation> content,
      @BeanParam ServiceParameters params)
      throws Exception {

    params.setEtag(content.getETag());
    return put(uriInfo, sb.putCompatibles(), content, params);
  }
}
