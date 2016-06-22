package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.SubTheme;

public class SubThemeController implements Controller{

   public void delete(CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      HibernateHelper.currentSession();.delete(subTheme);
   }

   public void create(CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      HibernateHelper.currentSession().save(subTheme);
   }

   public void update(CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      HibernateHelper.currentSession().update(subTheme);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.SubTheme as s where s.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<SubTheme> subThemeList = query.list();
      if (subThemeList.size() > 0){
         output = subThemeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.SubTheme";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<SubTheme> subThemeList = query.list();
      for (SubTheme subTheme : subThemeList) {
         output.add(subTheme);
      }
      return output;
   }
}