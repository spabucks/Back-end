package sphabucks.products.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sphabucks.products.model.ProductSearch;
import sphabucks.products.vo.IResponseSearchResult;
import sphabucks.products.vo.RequestSearchParam;
import sphabucks.products.vo.ResponseSearchResult;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public List<ProductSearch> searchProduct(RequestSearchParam requestSearchParam, Long page) {

        String jpql = "select p from ProductSearch p";
        Boolean isFirst = true;
        if (requestSearchParam.getKeyword() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.product.name LIKE :keyword";
        }
        if (requestSearchParam.getBigCategory() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            if (requestSearchParam.getBigCategory() == 0) {
                jpql += " 1 = 1";
            } else {
                jpql += " p.bigCategory.id = :bigCategory";
            }

        }
        if (requestSearchParam.getSize() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.product.size in :size";
        }
//        if (requestSearchParam.getPrice() != null) {
//            if (isFirst) {
//                jpql += " where";
//                isFirst = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " (p.product.price between :price and :price2)";
//        }
        if (requestSearchParam.getPrice() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " (";
            for (int i = 0; i < requestSearchParam.getPrice().size(); i++) {
                if (i != 0) {
                    jpql += " or";
                }
                jpql += " (p.product.price between :price" + i + " and :price" + i + "plus10000)";
            }
            jpql += " )";
        }





        if (requestSearchParam.getSmallCategory() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.smallCategory.id in :smallCategory";
        }
        if (requestSearchParam.getSeason() != null) {
            if (isFirst) {
                jpql += " where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " p.event.id in :event";
        }



        TypedQuery<ProductSearch> query = em.createQuery(jpql, ProductSearch.class)
                .setMaxResults(10);

        if(page != null) {
            query.setFirstResult(page.intValue()*10);
        }

        if (requestSearchParam.getKeyword() != null) {
            query.setParameter("keyword", "%"+requestSearchParam.getKeyword()+"%");
        }
        if (requestSearchParam.getBigCategory() != null && requestSearchParam.getBigCategory() != 0) {
            query.setParameter("bigCategory", requestSearchParam.getBigCategory());
        }
        if (requestSearchParam.getSize() != null) {
            query.setParameter("size", requestSearchParam.getSize());
        }
        if (requestSearchParam.getPrice() != null) {
            for (int i = 0; i < requestSearchParam.getPrice().size(); i++) {
                query.setParameter("price" + i, requestSearchParam.getPrice().get(i));
                query.setParameter("price" + i + "plus10000", requestSearchParam.getPrice().get(i) + 9999);
            }
        }
        if (requestSearchParam.getSmallCategory() != null) {
            query.setParameter("smallCategory", requestSearchParam.getSmallCategory());
        }
        if (requestSearchParam.getSeason() != null) {
            query.setParameter("event", requestSearchParam.getSeason());
        }

        return query.getResultList();
    }

}
