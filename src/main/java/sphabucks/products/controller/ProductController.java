package sphabucks.products.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sphabucks.products.model.Product;
import sphabucks.products.service.IProductService;
import sphabucks.products.vo.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@Tag(name = "상품")
public class ProductController {
    private final IProductService iProductService;

    @PostMapping("/v1/add")
    @Operation(summary = "상품 추가", description = "어드민 권한 - 삭제 예정?")
    public void addProduct(@RequestBody RequestProduct requestProduct) {
        iProductService.addProduct(requestProduct);
    }

    @GetMapping("/v1/get/{productId}")
    @Operation(summary = "상품 조회", description = "상품을 클릭했을 때 뜨는 상세정보")
    public ResponseProduct getProduct(@PathVariable Long productId) {
        return iProductService.getProduct(productId);
    }

    @GetMapping("/v1/get/all")
    @Operation(summary = "전체 상품 조회", description = "구현 X")
    @Tag(name = "검색")
    public List<Product> getAll(){
        return iProductService.getAll();
    }

    @GetMapping("/v1/get/allProduct")
    @Operation(summary = "전체상품조회(카테고리포함)", description = "구현 중 ")
    public List<ResponseSearchProduct> getAllProduct(Pageable pageable){
        return iProductService.getAllProducts(pageable);
    }

    // 베스트 상품 조회 메서드 (대분류 카테고리별 조회)
    @GetMapping("/v1/get-best/{bigCategoryId}")
    @Operation(summary = "베스트 상품 조회")
    @Tag(name = "검색")
    public List<ResponseProduct> getBestBigCategory(@PathVariable Long bigCategoryId) {

        return iProductService.getBestBigCategory(bigCategoryId);
    }
    // 상품 검색 메서드 (키워드 검색)
    @GetMapping("/v1/search")
    @Operation(summary = "키워드 검색", description = "돋보기 아이콘 클릭을 통해 들어간 검색창에서 키워드로 검색")
    @Tag(name = "검색")
    public List<ResponseSearchProduct> searchProductKeyword(@RequestParam("keyword") String keyword, @PageableDefault(size = 10) Pageable pageable){

        return iProductService.searchProductKeyword(keyword, pageable);
    }

    // 상품 검색 상단 필터 메뉴 호출 (키워드 검색)
    @GetMapping("/v1/search-menu")
    @Operation(summary = "키워드 검색 시 필터메뉴", description = "키워드로 검색할 시 필터메뉴목록 출력")
    @Tag(name = "검색")
    public ResponseSearchMenu searchProductKeywordMenu(@RequestParam("keyword") String keyword, Pageable pageable) {
        return iProductService.searchProductKeywordMenu(keyword, pageable);
    }

    @GetMapping("/v1/getBigCategory")
    @Operation(summary = "빅 카테고리 메뉴 불러오기 (전체, 케이크, 텀블러 ,,,)",
            description = "빅 카테고리 메뉴 불러오기 (전체 메뉴도 포함), 햄버거 메뉴 구현시 사용")
    @Tag(name = "검색")
    public List<ResponseBigCategory> getBigCategoryMenu() {
        return iProductService.getAllBigCategory();
    }

    @GetMapping("/v1/getSubCategory/{bigCategoryId}")
    @Operation(summary = "하위 카테고리 메뉴 불러오기 (전체, 케이크, 텀블러 ,,,)",
            description = "빅 카테고리 메뉴 클릭시 동적으로 하위 메뉴 구현시 사용")
    @Tag(name = "검색")
    public List<ResponseCategoryMenu> getSubCategoryMenu(@PathVariable Long bigCategoryId) {
        return iProductService.getAllSubCategory(bigCategoryId);
    }



}
