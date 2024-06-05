package persistencia;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Insumo;
import logica.Inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventarioJpaController implements Serializable {
    public InventarioJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public InventarioJpaController() {
        emf = Persistence.createEntityManagerFactory("default");
    }
    public void create(Inventario inventario) {
        // Verifica si la lista de clientes en la entidad Cliente es nula y la inicializa si es necesario
        if (inventario.getInsumos() == null) {
            inventario.setInsumos(new ArrayList<Insumo>());
        }
        EntityManager em = null;
        try {
            // Inicia la transacción con el EntityManager
            em = getEntityManager();
            em.getTransaction().begin();
            // Adjunta los automóviles al EntityManager
            List<Insumo> insumosAdjuntos = adjuntarInsumos(em, inventario.getInsumos());
            inventario.setInsumos(insumosAdjuntos);
            // Persiste la entidad Mecanica en la base de datos
            em.persist(inventario);
            // Confirma la transacción
            em.getTransaction().commit();
        } finally {
            // Cierra el EntityManager
            if (em != null) {
                em.close();
            }
        }
    }

    // Adjunta los automóviles al EntityManager
    private List<Insumo> adjuntarInsumos(EntityManager em, List<Insumo> insumos) {
        List<Insumo> clientesAdjuntos = new ArrayList<>();
        for (Insumo insumo : insumos) {
            // Obtiene una referencia a cada automóvil y lo agrega a la lista de automóviles adjuntos
            clientesAdjuntos.add(em.getReference(Insumo.class, insumo.getId()));
        }
        return clientesAdjuntos; // Devuelve la lista de automóviles adjuntos
    }

    public Inventario findInventario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inventario.class, id);
        } finally {
            em.close();
        }
    }





}
