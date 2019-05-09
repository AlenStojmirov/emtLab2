package mk.ukim.finki.emt.OnlineShop.Service.Impl;

import mk.ukim.finki.emt.OnlineShop.Domain.models.Manufacturer;
import mk.ukim.finki.emt.OnlineShop.Repository.ManufacturerRepository;
import mk.ukim.finki.emt.OnlineShop.Service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository repo;

    public ManufacturerServiceImpl(ManufacturerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Manufacturer> getAll(){
        return repo.findAll();
    }
}
