package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Set;

public class SetController implements Controller{

   public void delete(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession();.delete(set);
   }

   public void create(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession().save(set);
   }

   public void update(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession().update(set);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Set as s where s.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Set> setList = query.list();
      if (setList.size() > 0){
         output = setList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Set";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Set> setList = query.list();
      for (Set set : setList) {
         output.add(set);
      }
      return output;
   }
}