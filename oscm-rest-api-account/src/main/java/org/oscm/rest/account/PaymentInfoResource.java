package org.oscm.rest.account;

import org.oscm.rest.account.data.PaymentInfoRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/paymentinfos")
@Stateless
public class PaymentInfoResource extends RestResource {

        @EJB
        AccountBackend ab;

        @Since(CommonParams.VERSION_1)
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getPaymentInfos(@Context UriInfo uriInfo,
                @BeanParam AccountParameters params)
                throws Exception {
                return getCollection(uriInfo, ab.getPaymentInfoCollection(),
                        params);
        }

        @Since(CommonParams.VERSION_1)
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path(CommonParams.PATH_ID)
        public Response getPaymentInfo(@Context UriInfo uriInfo,
                @BeanParam AccountParameters params)
                throws Exception {
                return get(uriInfo, ab.getPaymentInfo(), params, true);
        }

        @Since(CommonParams.VERSION_1)
        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        @Path(CommonParams.PATH_ID)
        public Response updatePaymentInfo(
                @Context UriInfo uriInfo,
                PaymentInfoRepresentation content,
                @BeanParam AccountParameters params)
                throws Exception {
                return put(uriInfo, ab.putPaymentInfo(), content, params);
        }

        @Since(CommonParams.VERSION_1)
        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path(CommonParams.PATH_ID)
        public Response deletePaymentInfo(@Context UriInfo uriInfo,
                @BeanParam AccountParameters params)
                throws Exception {
                return delete(uriInfo, ab.deletePaymentInfo(), params);
        }
}
