package org.oscm.rest.marketplace;

import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.marketplace.data.MarketplaceRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MarketplaceBackend {

  @EJB MarketplaceService ms;

  public RestBackend.GetCollection<MarketplaceRepresentation, MarketplaceParameters>
      getCollection() {
    return new RestBackend.GetCollection<MarketplaceRepresentation, MarketplaceParameters>() {

      @Override
      public RepresentationCollection<MarketplaceRepresentation> getCollection(
          MarketplaceParameters params) throws Exception {
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
      }
    };
  }

  public RestBackend.Post<MarketplaceRepresentation, MarketplaceParameters> post() {
    return new RestBackend.Post<MarketplaceRepresentation, MarketplaceParameters>() {

      @Override
      public Object post(MarketplaceRepresentation content, MarketplaceParameters params)
          throws Exception {
        VOMarketplace mp = ms.createMarketplace(content.getVO());
        return Long.valueOf(mp.getKey());
      }
    };
  }

  public RestBackend.Put<MarketplaceRepresentation, MarketplaceParameters> put() {
    return new RestBackend.Put<MarketplaceRepresentation, MarketplaceParameters>() {

      @Override
      public boolean put(MarketplaceRepresentation content, MarketplaceParameters params)
          throws Exception {
        String mId = ms.getMarketplaceIdForKey(params.getId());
        VOMarketplace vo = content.getVO();
        vo.setMarketplaceId(mId);
        ms.updateMarketplace(vo);
        return true;
      }
    };
  }

  public RestBackend.Delete<MarketplaceParameters> delete() {
    return new RestBackend.Delete<MarketplaceParameters>() {

      @Override
      public boolean delete(MarketplaceParameters params) throws Exception {
        String mId = ms.getMarketplaceIdForKey(params.getId());
        ms.deleteMarketplace(mId);
        return true;
      }
    };
  }

  public RestBackend.Get<MarketplaceRepresentation, MarketplaceParameters> get() {
    return new RestBackend.Get<MarketplaceRepresentation, MarketplaceParameters>() {

      @Override
      public MarketplaceRepresentation get(MarketplaceParameters params) throws Exception {
        String mId = ms.getMarketplaceIdForKey(params.getId());
        VOMarketplace mp = ms.getMarketplaceById(mId);
        return new MarketplaceRepresentation(mp);
      }
    };
  }
}
