package com.sparta.springcore.service;

import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.repository.ProductRepository;
import com.sparta.springcore.dto.ProductRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public  ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

//신규상품등록
    public Product createProduct(ProductRequestDto requestDto) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.save(product);

        return product;
    }

    //설정가격변경
    // findById가 결과가 Optional<>로 되어 있어 Product 를 Optional 처리하던가
    // 그대로 두고  findById(id);뒤에 .orElseThrow 로 예외 처리를 해주거나 해야함!
    //Optional<Product> product = productRepository.findById(id);
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NullPointerException("해당아이디가 존재하지 않습니다"));
        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);
        return product;
    }

    //등록상품조회
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();

        return products;
    }
}
