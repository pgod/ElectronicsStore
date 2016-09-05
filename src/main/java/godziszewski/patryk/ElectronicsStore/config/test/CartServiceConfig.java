package godziszewski.patryk.ElectronicsStore.config.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import godziszewski.patryk.ElectronicsStore.domain.repository.CartRepository;

@Profile("CartService-test")
@Configuration
public class CartServiceConfig {
	@Bean
    @Primary
    public CartRepository cartRepository() {
        return Mockito.mock(CartRepository.class);
    }
}
