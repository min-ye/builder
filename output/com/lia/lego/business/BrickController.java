package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Brick;

public class BrickController implements Controller{

   public void delete(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession();.delete(brick);
   }

   public void create(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession().save(brick);
   }

   public void update(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession().update(brick);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Brick as b where b.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Brick> brickList = query.list();
      if (brickList.size() > 0){
         output = brickList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Brick";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Brick> brickList = query.list();
      for (Brick brick : brickList) {
         output.add(brick);
      }
      return output;
   }
}