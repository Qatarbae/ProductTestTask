package dev.task.producttesttask.config;

import dev.task.producttesttask.entity.*;
import dev.task.producttesttask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TvRepository tvModelRepository;
    @Autowired
    private VacuumCleanerRepository vacuumCleanerModelRepository;

    @Autowired
    private RefrigeratorRepository refrigeratorRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ComputerRepository computerRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            createAndSaveData();
        };
    }

    private void createAndSaveData() {
        for (int i = 0; i < 6; i++) {
            ProductEntity product;
            ProductType productType = null;
            switch (i) {
                case 0:
                    productType = ProductType.TV;
                    break;
                case 1:
                    productType = ProductType.VACUUM_CLEANER;
                    break;
                case 2:
                    productType = ProductType.REFRIGERATOR;
                    break;
                case 3:
                    productType = ProductType.PHONE;
                    break;
                case 4:
                    productType = ProductType.COMPUTER;
                    break;
                case 5:
                    productType = ProductType.TV;
                    break;
            }
            product = createProduct(
                    productType,
                    "Product " + (int) (Math.floor(Math.random() * 100)),
                    "Country " + (int) (Math.floor(Math.random() * 100)),
                    "Manufacturer " + (int) (Math.floor(Math.random() * 100)),
                    true,
                    true);
            productRepository.save(product);
            for (int j = 0; j < 3; j++) {
                ModelEntity model;
                if (i == 0) {
                    model = createTvModel(
                            productType,
                            "TV Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "TV SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "TV Color " + (int) (Math.floor(Math.random() * 100)),
                            "TV Size " + (int) (Math.floor(Math.random() * 100)),
                            100.0 * (int) (Math.floor(Math.random() * 100)),
                            true, product,
                            "TV Category " + (int) (Math.floor(Math.random() * 100)),
                            "TV Technology " + (int) (Math.floor(Math.random() * 100)));
                    tvModelRepository.save((TvModelEntity) model);
                } else if (i == 1) {
                    model = createVacuumCleanerModel(
                            productType,
                            "Vacuum Cleaner Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "Vacuum Cleaner SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "Vacuum Cleaner Color " + (int) (Math.floor(Math.random() * 100)),
                            "Vacuum Cleaner Size " + (int) (Math.floor(Math.random() * 100)),
                            100.0 * (int) (Math.floor(Math.random() * 100)),
                            true, product,
                            50.0 * (int) (Math.floor(Math.random() * 100)),
                            (int) (Math.floor(Math.random() * 10)));
                    vacuumCleanerModelRepository.save((VacuumCleanerModelEntity) model);
                } else if (i == 2) {
                    model = createRefrigeratorModel(
                            productType,
                            "Refrigerator Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "Refrigerator SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "Refrigerator Color " + (int) (Math.floor(Math.random() * 100)),
                            "Refrigerator Size " + (int) (Math.floor(Math.random() * 100)),
                            200.0 * (int) (Math.floor(Math.random() * 10)),
                            true, product,
                            (int) (Math.floor(Math.random() * 10)),
                            "Refrigerator Compressor Type " + (int) (Math.floor(Math.random() * 100)));
                    refrigeratorRepository.save((RefrigeratorModelEntity) model);
                } else if (i == 3) {
                    model = createPhoneModel(
                            productType,
                            "Phone Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "Phone SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "Phone Color " + (int) (Math.floor(Math.random() * 100)),
                            "Phone Size " + (int) (Math.floor(Math.random() * 100)),
                            500.0 * (int) (Math.floor(Math.random() * 100)),
                            true, product,
                            (int) (Math.floor(Math.random() * 10)),
                            (int) (Math.floor(Math.random() * 10)));
                    phoneRepository.save((PhoneModelEntity) model);
                } else if (i == 4) {
                    model = createComputerModel(
                            productType,
                            "Computer Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "Computer SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "Computer Color " + (int) (Math.floor(Math.random() * 100)),
                            "Computer Size " + (int) (Math.floor(Math.random() * 100)),
                            1000.0 * (int) (Math.floor(Math.random() * 100)),
                            true, product,
                            "Computer Category " + (int) (Math.floor(Math.random() * 10)),
                            "Computer Processor Type " + (int) (Math.floor(Math.random() * 10)));
                    computerRepository.save((ComputerModelEntity) model);
                } else {
                    model = createTvModel(
                            productType,
                            "TV Model " + (int) (Math.floor(Math.random() * 100)) + "-" + (j + 1),
                            "TV SerialNumber " + (int) (Math.floor(Math.random() * 10000)),
                            "TV Color " + (int) (Math.floor(Math.random() * 100)),
                            "TV Size " + (int) (Math.floor(Math.random() * 100)),
                            100.0 * (int) (Math.floor(Math.random() * 100)),
                            true, product,
                            "TV Category " + (int) (Math.floor(Math.random() * 100)),
                            "TV Technology " + (int) (Math.floor(Math.random() * 100)));
                    tvModelRepository.save((TvModelEntity) model);
                }
            }
        }
    }

    private ProductEntity createProduct(ProductType type, String name, String country, String manufacturer, boolean onlineOrderAvailable, boolean installmentAvailable) {
        return new ProductEntity(type, name, country, manufacturer, onlineOrderAvailable, installmentAvailable);
    }


    private TvModelEntity createTvModel(
            ProductType productType,
            String name,
            String serialNumber,
            String color,
            String size,
            double price,
            boolean available,
            ProductEntity product,
            String category,
            String technology) {
        TvModelEntity tvModel = new TvModelEntity();
        tvModel.setModelType(productType);
        tvModel.setName(name);
        tvModel.setSerialNumber(serialNumber);
        tvModel.setColor(color);
        tvModel.setSize(size);
        tvModel.setPrice(price);
        tvModel.setAvailable(available);
        tvModel.setProduct(product);
        tvModel.setCategory(category);
        tvModel.setTechnology(technology);
        return tvModel;
    }

    private VacuumCleanerModelEntity createVacuumCleanerModel(
            ProductType productType,
            String name,
            String serialNumber,
            String color,
            String size,
            double price,
            boolean available,
            ProductEntity product,
            double dustBagCapacity,
            int modesCount) {
        VacuumCleanerModelEntity vacuumCleanerModel = new VacuumCleanerModelEntity();
        vacuumCleanerModel.setModelType(productType);
        vacuumCleanerModel.setName(name);
        vacuumCleanerModel.setSerialNumber(serialNumber);
        vacuumCleanerModel.setColor(color);
        vacuumCleanerModel.setSize(size);
        vacuumCleanerModel.setPrice(price);
        vacuumCleanerModel.setAvailable(available);
        vacuumCleanerModel.setProduct(product);
        vacuumCleanerModel.setDustBagCapacity(dustBagCapacity);
        vacuumCleanerModel.setModesCount(modesCount);
        return vacuumCleanerModel;
    }

    private RefrigeratorModelEntity createRefrigeratorModel(
            ProductType productType,
            String name,
            String serialNumber,
            String color,
            String size,
            double price,
            boolean available,
            ProductEntity product,
            int doorsCount,
            String compressorType) {
        RefrigeratorModelEntity refrigeratorModel = new RefrigeratorModelEntity();
        refrigeratorModel.setModelType(productType);
        refrigeratorModel.setName(name);
        refrigeratorModel.setSerialNumber(serialNumber);
        refrigeratorModel.setColor(color);
        refrigeratorModel.setSize(size);
        refrigeratorModel.setPrice(price);
        refrigeratorModel.setAvailable(available);
        refrigeratorModel.setProduct(product);
        refrigeratorModel.setDoorsCount(doorsCount);
        refrigeratorModel.setCompressorType(compressorType);
        return refrigeratorModel;
    }

    private PhoneModelEntity createPhoneModel(
            ProductType productType,
            String name,
            String serialNumber,
            String color,
            String size,
            double price,
            boolean available,
            ProductEntity product,
            int memory,
            int cameraCount) {
        PhoneModelEntity phoneModel = new PhoneModelEntity();
        phoneModel.setModelType(productType);
        phoneModel.setName(name);
        phoneModel.setSerialNumber(serialNumber);
        phoneModel.setColor(color);
        phoneModel.setSize(size);
        phoneModel.setPrice(price);
        phoneModel.setAvailable(available);
        phoneModel.setProduct(product);
        phoneModel.setMemory(memory);
        phoneModel.setCameraCount(cameraCount);
        return phoneModel;
    }

    private ComputerModelEntity createComputerModel(
            ProductType productType,
            String name,
            String serialNumber,
            String color,
            String size,
            double price,
            boolean available,
            ProductEntity product,
            String category,
            String processorType) {
        ComputerModelEntity computerModel = new ComputerModelEntity();
        computerModel.setModelType(productType);
        computerModel.setName(name);
        computerModel.setSerialNumber(serialNumber);
        computerModel.setColor(color);
        computerModel.setSize(size);
        computerModel.setPrice(price);
        computerModel.setAvailable(available);
        computerModel.setProduct(product);
        computerModel.setCategory(category);
        computerModel.setProcessorType(processorType);
        return computerModel;
    }
}
