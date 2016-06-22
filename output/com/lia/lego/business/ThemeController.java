package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Theme;

public class ThemeController implements Controller{

   public void delete(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession();.delete(theme);
   }

   public void create(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession().save(theme);
   }

   public void update(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession().update(theme);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Theme as t where t.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Theme> themeList = query.list();
      if (themeList.size() > 0){
         output = themeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Theme";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Theme> themeList = query.list();
      for (Theme theme : themeList) {
         output.add(theme);
      }
      return output;
   }
}