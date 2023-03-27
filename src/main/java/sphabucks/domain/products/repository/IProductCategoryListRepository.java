package sphabucks.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sphabucks.domain.products.model.ProductCategoryList;

import java.util.List;

public interface IProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {
    List<ProductCategoryList> findAllByProductId(Long productId);

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    List<ProductCategoryList> findTop30ByBigCategoryId(Long bigCategoryId);

    // 소분류 카테고리별 조회
    List<ProductCategoryList> findAllBySmallCategoryId(Long sCategoryId);

    ProductCategoryList findByProductId(Long productId);
}