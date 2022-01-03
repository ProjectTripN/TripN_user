package shop.tripn.api.common;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface CommonController<R, T> {
    ResponseEntity<List<R>> findAll();
    ResponseEntity<R> getById(T id);
//    ResponseEntity<String> save(R T);
    ResponseEntity<Optional<R>> findById(T id);
    ResponseEntity<Boolean> existById(T id);
    ResponseEntity<Long> count();
    ResponseEntity<String> deleteById(T id);
}
