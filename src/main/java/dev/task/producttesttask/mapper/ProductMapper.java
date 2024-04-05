package dev.task.producttesttask.mapper;

import dev.task.producttesttask.entity.*;
import dev.task.producttesttask.entity.DTO.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto toDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setManufacturerCountry(productEntity.getManufacturerCountry());
        productDto.setManufacturer(productEntity.getManufacturer());
        productDto.setInstallmentAvailable(productEntity.isInstallmentAvailable());
        productDto.setOnlineOrderAvailable(productEntity.isOnlineOrderAvailable());

        List<ModelEntity> allModels = new ArrayList<>();
        allModels.addAll(productEntity.getTvModels());
        allModels.addAll(productEntity.getVacuumCleanerModels());
        allModels.addAll(productEntity.getRefrigeratorModels());
        allModels.addAll(productEntity.getPhoneModels());
        allModels.addAll(productEntity.getComputerModels());

        List<ModelDto> modelDtoList = allModels.stream()
                .map(this::toModelDto)
                .collect(Collectors.toList());

        productDto.setModels(modelDtoList);
        return productDto;
    }

    public ModelDto toModelDto(ModelEntity modelEntity) {
        ModelDto modelDto = new ModelDto();
        if (modelEntity instanceof TvModelEntity) {
            TvModelEntity tvModelEntity = (TvModelEntity) modelEntity;
            TvModelDto tvModelDto = new TvModelDto();
            tvModelDto.setCategory(tvModelEntity.getCategory());
            tvModelDto.setTechnology(tvModelEntity.getTechnology());
            modelDto = tvModelDto;
        } else if (modelEntity instanceof VacuumCleanerModelEntity) {
            VacuumCleanerModelEntity vacuumCleanerModelEntity = (VacuumCleanerModelEntity) modelEntity;
            VacuumCleanerModelDto vacuumCleanerModelDto = new VacuumCleanerModelDto();
            vacuumCleanerModelDto.setDustBagCapacity(vacuumCleanerModelEntity.getDustBagCapacity());
            vacuumCleanerModelDto.setModesCount(vacuumCleanerModelEntity.getModesCount());
            modelDto = vacuumCleanerModelDto;
        } else if (modelEntity instanceof RefrigeratorModelEntity) {
            RefrigeratorModelEntity refrigeratorModelEntity = (RefrigeratorModelEntity) modelEntity;
            RefrigeratorModelDto refrigeratorModelDto = new RefrigeratorModelDto();
            refrigeratorModelDto.setDoorsCount(refrigeratorModelEntity.getDoorsCount());
            refrigeratorModelDto.setCompressorType(refrigeratorModelEntity.getCompressorType());
            modelDto = refrigeratorModelDto;
        } else if (modelEntity instanceof PhoneModelEntity) {
            PhoneModelEntity phoneModelEntity = (PhoneModelEntity) modelEntity;
            PhoneModelDto phoneModelDto = new PhoneModelDto();
            phoneModelDto.setMemory(phoneModelEntity.getMemory());
            phoneModelDto.setCameraCount(phoneModelEntity.getCameraCount());
            modelDto = phoneModelDto;
        } else if (modelEntity instanceof ComputerModelEntity) {
            ComputerModelEntity computerModelEntity = (ComputerModelEntity) modelEntity;
            ComputerModelDto computerModelDto = new ComputerModelDto();
            computerModelDto.setCategory(computerModelEntity.getCategory());
            computerModelDto.setProcessorType(computerModelEntity.getProcessorType());
            modelDto = computerModelDto;
        }
        modelDto.setId(modelEntity.getId());
        modelDto.setName(modelEntity.getName());
        modelDto.setSerialNumber(modelEntity.getSerialNumber());
        modelDto.setColor(modelEntity.getColor());
        modelDto.setSize(modelEntity.getSize());
        modelDto.setPrice(modelEntity.getPrice());
        modelDto.setAvailable(modelEntity.isAvailable());
        return modelDto;
    }
}
