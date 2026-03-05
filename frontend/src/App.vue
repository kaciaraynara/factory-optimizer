<template>
  <div class="app-container">
    <header class="main-header">
      <h1>Factory Optimization System</h1>
      <p class="subtitle">R&D Production Management Tool</p>
    </header>

    <main class="dashboard-grid">
      <section class="panel">
        <div class="panel-header">
          <span class="icon">📦</span>
          <h2>Inventory Management</h2>
        </div>
        
        <div class="form-card">
          <input v-model="newMaterial.name" placeholder="Material Name" class="custom-input" />
          <input v-model.number="newMaterial.stockQuantity" type="number" placeholder="Quantity" class="custom-input" />
          <button @click="saveMaterial" class="btn btn-primary">Add Material</button>
        </div>

        <div class="list-section">
          <h3>Current Stock:</h3>
          <ul class="styled-list">
            <li v-for="m in materials" :key="m.id">
              <span class="item-name">{{ m.name }}</span>
              <span class="item-qty">{{ m.stockQuantity }} units</span>
            </li>
          </ul>
        </div>
      </section>

      <section class="panel">
        <div class="panel-header">
          <span class="icon">🛠️</span>
          <h2>Product Designer</h2>
        </div>

        <div class="form-card">
          <input v-model="newProduct.name" placeholder="Product Name" class="custom-input" />
          <input v-model.number="newProduct.price" type="number" placeholder="Sales Price ($)" class="custom-input" />
          
          <div class="composition-row">
            <select v-model="selectedMaterialId" class="custom-select">
              <option value="" disabled>Select a material</option>
              <option v-for="m in materials" :key="m.id" :value="m.id">{{ m.name }}</option>
            </select>
            <input v-model.number="requiredQty" type="number" placeholder="Qty" class="custom-input qty-input" />
            <button @click="addIngredient" class="btn btn-secondary">Add</button>
          </div>

          <button @click="saveProduct" class="btn btn-success full-width">Save Product</button>
        </div>
      </section>

      <section class="panel full-width">
        <div class="panel-header">
          <span class="icon">📊</span>
          <h2>Optimization Engine</h2>
        </div>
        
        <button @click="runOptimization" class="btn btn-optimize">Run Profit Maximizer</button>
        
        <div v-if="Object.keys(plan).length > 0" class="result-box">
          <div class="plan-cards">
            <div v-for="(qty, name) in plan" :key="name" class="plan-card">
              <span class="plan-name">{{ name }}</span>
              <span class="plan-qty">{{ qty }} units</span>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'; 
import api from './api';

const materials = ref([]);
const plan = ref({});
const newMaterial = ref({ name: '', stockQuantity: 0 });
const newProduct = ref({ name: '', price: 0, composition: [] });
const selectedMaterialId = ref('');
const requiredQty = ref(0);

const fetchData = async () => {
  try {
    const res = await api.get('/materials');
    materials.value = res.data;
  } catch (e) { console.error("Error fetching data", e); }
};

const saveMaterial = async () => {
  if (!newMaterial.value.name) return;
  await api.post('/materials', newMaterial.value);
  newMaterial.value = { name: '', stockQuantity: 0 };
  fetchData();
};

const addIngredient = () => {
  if(!selectedMaterialId.value || requiredQty.value <= 0) return;
  newProduct.value.composition.push({
    material: { id: selectedMaterialId.value },
    quantityRequired: requiredQty.value
  });
  selectedMaterialId.value = '';
  requiredQty.value = 0;
};

const saveProduct = async () => {
  if (!newProduct.value.name || newProduct.value.composition.length === 0) return;
  await api.post('/products', newProduct.value);
  newProduct.value = { name: '', price: 0, composition: [] };
  alert("Product Saved Successfully!");
};

const runOptimization = async () => {
  const res = await api.get('/products/optimize');
  plan.value = res.data;
};

onMounted(fetchData);
</script>

<style scoped>
/* CSS DE ALTO CONTRASTE PARA VISIBILIDADE MÁXIMA */
.app-container {
  font-family: 'Segoe UI', system-ui, sans-serif;
  background-color: #f0f4f8;
  min-height: 100vh;
  padding: 40px 20px;
  color: #1a202c;
}
.main-header { text-align: center; margin-bottom: 40px; }
.main-header h1 { font-size: 2.5rem; color: #2d3748; margin: 0; }
.dashboard-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(400px, 1fr)); gap: 25px; max-width: 1200px; margin: 0 auto; }
.panel { background: white; border-radius: 12px; padding: 30px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); border: 2px solid #cbd5e1; }
.full-width { grid-column: 1 / -1; }
.panel-header { display: flex; align-items: center; gap: 10px; margin-bottom: 25px; border-bottom: 2px solid #f7fafc; padding-bottom: 15px; }

/* FOCO NA VISIBILIDADE DOS INPUTS */
.custom-input, .custom-select {
  padding: 12px 16px;
  border: 2px solid #2d3748; 
  border-radius: 8px;
  font-size: 1rem;
  background-color: #ffffff;
  color: #000000; 
  font-weight: bold;
}
.custom-input::placeholder { color: #718096; }

.btn { padding: 12px 20px; border: none; border-radius: 8px; font-weight: 700; cursor: pointer; transition: 0.2s; }
.btn-primary { background: #3182ce; color: white; }
.btn-secondary { background: #718096; color: white; }
.btn-success { background: #38a169; color: white; }
.btn-optimize { background: #2d3748; color: white; width: 100%; font-size: 1.2rem; }
.styled-list li { background: #edf2f7; padding: 12px; margin-bottom: 8px; border-radius: 6px; display: flex; justify-content: space-between; font-weight: 600; color: #2d3748; }
.plan-card { background: #c6f6d5; padding: 20px; border-radius: 8px; border-left: 5px solid #38a169; text-align: center; }
</style>