package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistOrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<PersistOrderDTO, Long> {
    @Query(nativeQuery = true, value = "select oroi.persist_orderdto_id as id, " +
            "orr.order_status, " +
            "oi.amount, " +
            "mi.id as menu_item_id, " +
            "mi.name " +
            "from order_item oi " +
            "inner join menu_item mi on oi.menu_Item_name = mi.name " +
            "and mi.location_id = :locationId " +
            "inner join order_round_order_items oroi on oroi.order_items_id = oi.id " +
            "inner join order_round orr ON orr.id = oroi.persist_orderdto_id " +
            "where orr.order_status = 'PENDING' OR orr.order_status = 'PREPARING'")
    List<PersistOrderDTO> findAllActiveOrdersByLocationId(Long locationId);

    @Query(nativeQuery = true, value = "select oroi.persist_orderdto_id as id, orr.order_status, " +
            "oi.amount, " +
            "mi.id as menu_item_id, " +
            "mi.name " +
            "from order_item oi " +
            "inner join menu_item mi on oi.menu_Item_name = mi.name " +
            "and mi.location_id = :locationId " +
            "inner join order_round_order_items oroi on oroi.order_items_id = oi.id " +
            "inner join order_round orr ON orr.id = oroi.persist_orderdto_id")
    List<PersistOrderDTO> findAllOrdersByLocationId(Long locationId);
    
}
