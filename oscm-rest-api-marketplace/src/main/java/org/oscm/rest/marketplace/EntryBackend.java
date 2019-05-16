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

import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.vo.VOCatalogEntry;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.marketplace.data.EntryRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;

@Stateless
public class EntryBackend {

  @EJB MarketplaceService ms;

  public RestBackend.Put<EntryRepresentation, MarketplaceParameters> put() {
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
