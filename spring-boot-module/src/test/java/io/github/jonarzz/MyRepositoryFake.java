package io.github.jonarzz;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.*;

import java.util.*;
import java.util.function.*;

// should not be treated as a Spring bean and should not be loaded into the Spring Context
@ConditionalOnExpression("false")
class MyRepositoryFake implements MyRepository {

    private MyRepository delegate;

    MyRepositoryFake(MyRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<MyEntity> findById(Long id) {
        // all methods spied on in the tests should be delegated like this one
        // if you're using Lombok, consider https://projectlombok.org/features/experimental/Delegate
        return delegate.findById(id);
    }

    @Override
    public List<MyEntity> findAll() {
        return null;
    }

    @Override
    public List<MyEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<MyEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<MyEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MyEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MyEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends MyEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends MyEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends MyEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MyEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<MyEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MyEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public MyEntity getById(Long aLong) {
        return null;
    }

    @Override
    public MyEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends MyEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MyEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends MyEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends MyEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MyEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MyEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MyEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
