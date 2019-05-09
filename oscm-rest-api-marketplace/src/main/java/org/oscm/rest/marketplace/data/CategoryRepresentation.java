/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.marketplace.data;

import org.oscm.internal.vo.VOCategory;
import org.oscm.rest.common.Representation;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepresentation extends Representation {

  private transient VOCategory vo;

  private String categoryId;

  public CategoryRepresentation() {
    this(new VOCategory());
  }

  public CategoryRepresentation(VOCategory cat) {
    vo = cat;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setCategoryId(getCategoryId());
    vo.setKey(convertIdToKey());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setCategoryId(vo.getCategoryId());
    setETag(Long.valueOf(vo.getVersion()));
    setId(Long.valueOf(vo.getKey()));
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public VOCategory getVO() {
    return vo;
  }

  public static List<VOCategory> update(List<CategoryRepresentation> cats) {
    List<VOCategory> result = new ArrayList<VOCategory>();
    if (cats == null) {
      return result;
    }
    for (CategoryRepresentation cr : cats) {
      cr.update();
      result.add(cr.getVO());
    }
    return result;
  }

  public static List<CategoryRepresentation> convert(List<VOCategory> cats) {
    List<CategoryRepresentation> result = new ArrayList<CategoryRepresentation>();
    if (cats == null) {
      return result;
    }
    for (VOCategory cat : cats) {
      CategoryRepresentation cr = new CategoryRepresentation(cat);
      cr.convert();
      result.add(cr);
    }
    return result;
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  protected long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  protected int convertETagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
