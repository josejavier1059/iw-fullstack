package es.uca.iw.fullstackwebapp.option.services;

import es.uca.iw.fullstackwebapp.option.domain.Option;
import es.uca.iw.fullstackwebapp.option.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Optional<Option> getOptionById(Long id) {
        return optionRepository.findById(id);
    }

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }
}
