package com.op.cookcloud.controller.crud;


/**
 * User: alexander.pastukhov
 * Date: 11/28/13
 * Time: 7:42 PM
 */
public interface CRUDController<E> {

    E read(Integer e);

    void update(E e);

    void create(E e);

    void delete(Integer id);

}
