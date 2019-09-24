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

import java.util.Collections;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.types.exception.NonUniqueBusinessKeyException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.types.exception.OperationNotPermittedException;
import org.oscm.internal.types.exception.ValidationException;
import org.oscm.internal.vo.VOCatalogEntry;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.EntryRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

@Stateless
public class EntryBackend {

  @EJB MarketplaceService ms;

  public RestBackend.Put<EntryRepresentation, MarketplaceParameters> put()
      throws ObjectNotFoundException, ValidationException, NonUniqueBusinessKeyException,
          OperationNotPermittedException {
    return (content, params) -> {
      VOCatalogEntry ce = content.getVO();
      String mId = ms.getMarketplaceIdForKey(params.getId());
      VOMarketplace mp = new VOMarketplace();
      mp.setKey(params.getId().longValue());
      mp.setMarketplaceId(mId);
      ce.setMarketplace(mp);

      ms.publishService(params.getService(), Collections.singletonList(ce));
      return true;
    };
  }
}
