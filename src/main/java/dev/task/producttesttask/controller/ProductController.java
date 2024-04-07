package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterProductSearch;
import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products/")
@Tag(
        name = "Продукты",
        description = "контроллеры для работы с продуктами"
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @Transactional
    @Operation(summary = "Получение всех товаров",
            parameters = {
                    @Parameter(name = "filter", description = "Фильтр по имени", required = false)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный ответ",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = ProductDto.class
                                                    )
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found"
                    )
            }
    )
    public ResponseEntity<Iterable<ProductDto>> getProducts(
            @RequestParam(name = "filter", required = false) String filter
    ) {
        return ResponseEntity.ok().body(productService.getAllProducts(filter));
    }

    @PostMapping("/filter")
    @Transactional
    @Operation(summary = "Фильтрация товаров",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = FilterProductSearch.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный ответ",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = ProductDto.class
                                                    )
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found"
                    )
            }
    )
    public ResponseEntity<Iterable<ProductDto>> getProductsSearch(
            @Valid @RequestBody FilterProductSearch filter,
            BindingResult bindingResult
    ) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Iterable<ProductDto> tvModelDto = productService.getAllProductsFilter(filter);
            return ResponseEntity.ok(tvModelDto);
        }
    }

    @GetMapping("{productId:\\d+}/product")
    @Transactional
    @Operation(summary = "Получение информации о товаре по ID",
            parameters = {
                    @Parameter(name = "productId", description = "ID товара", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = ProductDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found"
                    )
            }
    )
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long produсtId) {
        return ResponseEntity.ok().body(this.productService.getProductById(produсtId));
    }

    @PostMapping("")
    @Transactional
    @Operation(summary = "Создать вид товара",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = NewProductPayload.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = ProductEntity.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product has not been created")
            })
    public ResponseEntity<?> createProduct(
            @RequestBody NewProductPayload payload) {
        ProductEntity product = this.productService.createProduct(payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("{productId:\\d+}")
    @Transactional
    @Operation(summary = "Удаление товара",
            parameters = {
                    @Parameter(name = "productId", description = "ID товара", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found")
            }
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
