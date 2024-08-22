package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       if (partRepository.count() == 0) {
           OutsourcedPart engine = new OutsourcedPart();
           engine.setCompanyName("Pratt & Whitney");
           engine.setName("F-135 Turbofan Engine");
           engine.setInv(50);
           engine.setPrice(65000.0);
           engine.setMinInv(0);
           engine.setMaxInv(60);
           outsourcedPartRepository.save(engine);

           InhousePart argus_is = new InhousePart();
           argus_is.setPartId(2);
           argus_is.setName("1.8 GigaPixel Drone Camera");
           argus_is.setPrice(18500);
           argus_is.setInv(60);
           argus_is.setMinInv(5);
           argus_is.setMaxInv(70);
           partRepository.save(argus_is);

           InhousePart antenna = new InhousePart();
           antenna.setPartId(3);
           antenna.setName("Phased Array Antenna");
           antenna.setPrice(1000);
           antenna.setInv(70);
           antenna.setMinInv(10);
           antenna.setMaxInv(80);
           partRepository.save(antenna);

           InhousePart helmet = new InhousePart();
           helmet.setPartId(1);
           helmet.setName("Helmet Mounted Display");
           helmet.setPrice(400);
           helmet.setInv(80);
           helmet.setMinInv(15);
           helmet.setMaxInv(90);
           partRepository.save(helmet);

           OutsourcedPart landing_gears = new OutsourcedPart();
           landing_gears.setCompanyName("Goodrich");
           landing_gears.setName("Landing Gears");
           landing_gears.setInv(90);
           landing_gears.setPrice(600.0);
           landing_gears.setId(100L);
           landing_gears.setMinInv(20);
           landing_gears.setMaxInv(100);
           outsourcedPartRepository.save(landing_gears);
       }
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(!(part.getName().isEmpty()))thePart=part;
            System.out.println(thePart.getCompanyName());
        }

        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        if (productRepository.count() == 0) {

            Product f35 = new Product("F-35B Lightning II", 265000, 80);
            Product f22 = new Product("F-22 Raptor", 145000, 70);
            Product f16 = new Product("F-16 Fighting Falcon", 63000, 150);
            Product sr71 = new Product("SR-71 BlackBird", 34000, 220);
            Product mq9 = new Product("MQ-9 Reaper", 32000, 370);

            productRepository.save(f35);
            productRepository.save(f22);
            productRepository.save(f16);
            productRepository.save(sr71);
            productRepository.save(mq9);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products "+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts "+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
