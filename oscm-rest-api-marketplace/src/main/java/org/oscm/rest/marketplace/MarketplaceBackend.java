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

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.marketplace.data.MarketplaceRepresentation;

@Stateless
public class MarketplaceBackend {

  @EJB MarketplaceService ms;

  public RestBackend.GetCollection<MarketplaceRepresentation, MarketplaceParameters>
      getCollection() {
    return params -> {
      List<VOMarketplace> mps;
      switch (params.getListType()) {
        case ALL:
          mps = ms.getMarketplacesForOperator();
          break;
        case PUBLISH:
          mps = ms.getMarketplacesForOrganization();
          break;
        case ACCESSIBLE:
          mps = ms.getAccessibleMarketplaces();
          break;
        case OWNED:
        default:
          mps = ms.getMarketplacesOwned();
          break;
      }
      return new RepresentationCollection<MarketplaceRepresentation>(
          MarketplaceRepresentation.toCollection(mps));
    };
  }

  public RestBackend.Post<MarketplaceRepresentation, MarketplaceParameters> post() {
    return (content, params) -> {
      VOMarketplace mp = ms.createMarketplace(content.getVO());
      return Long.valueOf(mp.getKey());
    };
  }

  public RestBackend.Put<MarketplaceRepresentation, MarketplaceParameters> put() {
    return (content, params) -> {
      String mId = ms.getMarketplaceIdForKey(params.getId());
      VOMarketplace vo = content.getVO();
      vo.setMarketplaceId(mId);
      ms.updateMarketplace(vo);
      return true;
    };
  }

  public RestBackend.Delete<MarketplaceParameters> delete() {
    return params -> {
      String mId = ms.getMarketplaceIdForKey(params.getId());
      ms.deleteMarketplace(mId);
      return true;
    };
  }

  public RestBackend.Get<MarketplaceRepresentation, MarketplaceParameters> get() {
    return params -> {
      String mId = ms.getMarketplaceIdForKey(params.getId());
      VOMarketplace mp = ms.getMarketplaceById(mId);
      return new MarketplaceRepresentation(mp);
    };
  }
}
