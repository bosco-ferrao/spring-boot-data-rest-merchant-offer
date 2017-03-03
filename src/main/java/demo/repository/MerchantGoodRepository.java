package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import demo.model.MerchantGood;

@RepositoryRestResource
public interface MerchantGoodRepository extends CrudRepository<MerchantGood, Long> {

}
