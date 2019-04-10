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

        @EJB
        MarketplaceService ms;

        public RestBackend.Put<EntryRepresentation, MarketplaceParameters> put() {
                return new RestBackend.Put<EntryRepresentation, MarketplaceParameters>() {

                        @Override
                        public boolean put(EntryRepresentation content,
                                MarketplaceParameters params)
                                throws Exception {
                                VOCatalogEntry ce = content.getVO();
                                String mId = ms
                                        .getMarketplaceIdForKey(params.getId());
                                VOMarketplace mp = new VOMarketplace();
                                mp.setKey(params.getId().longValue());
                                mp.setMarketplaceId(mId);
                                ce.setMarketplace(mp);

                                ms.publishService(params.getService(),
                                        Collections.singletonList(ce));
                                return true;
                        }
                };
        }
}
