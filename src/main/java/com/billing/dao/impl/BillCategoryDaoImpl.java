package com.billing.dao.impl;

import com.billing.dao.BillCategoryDao;
import com.billing.helper.Response;
import com.billing.model.BillCategory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class BillCategoryDaoImpl extends BaseDaoImpl implements BillCategoryDao {

    @Autowired
    public BillCategoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(BillCategory billCategory) {
        return super.save(billCategory);
    }

    @Override
    public Response update(BillCategory billCategory) {
        return super.update(billCategory);
    }

    @Override
    public Response<BillCategory> findById(int id) {
        try {
            return Response.Success(getCurrentSession().get(BillCategory.class, id));
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<List<BillCategory>> findByName(String name, boolean ignoreCase) {
        try {
            String queryString = ignoreCase ? "from BillCategory b where lower(b.name) = lower(:name)"
                                            : "from BillCategory b where b.name = :name";
            Query query = getCurrentSession().createQuery(queryString).setParameter("name", name);
            return Response.Success(query.getResultList());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<List<BillCategory>> findAll() {
        try {
            return Response.Success(getCurrentSession().createQuery("from BillCategory").list());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }
}
