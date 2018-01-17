package com.billing.dao.impl;

import com.billing.dao.BillCategoryDao;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillCategoryDaoImpl extends BaseDaoImpl implements BillCategoryDao{

    @Autowired
    public BillCategoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(BillCategory billCategory) {
        return super.save(billCategory);
    }

    @Override
    public Response deleteByCardId(int billCategoryId) {
        return super.delete(getCurrentSession().get(BillCategory.class, billCategoryId));
    }

    @Override
    public Response update(BillCategory billCategory) {
        return super.update(billCategory);
    }
}
