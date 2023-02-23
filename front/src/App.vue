<template>
  <h1>Products</h1>
  <products v-bind:products="productslist" @edit:product="editProduct" @delete:product="deleteProduct" @add:product="addProduct"/>
</template>

<script>
import Products from './components/Products.vue';

export default {
  name: 'App',
  components: {
    Products
  },
  data() {
    return {
      productslist: [],
    }
  },
  methods: {
    async addProduct(product) {
      try {
        const response = await fetch('http://localhost:8081/Products/Add', {
          method: 'POST',
          body: JSON.stringify(product),
          headers: { "Content-type": "application/json; charset=UTF-8" }
        });
        // eslint-disable-next-line no-unused-vars
        const data = await response.json()
        this.getProducts()
      } catch (error) {
        console.error('Error occured while adding product: ' +error)
      }
    },

    async editProduct(id, updatedProduct) {
      try {
        const response = await fetch (`http://localhost:8081/Products/Update/${id}`, {
          method: 'PUT',
          body: JSON.stringify(updatedProduct),
          headers: { "Content-type": "application/json; charset=UTF-8" } 
        });

        const data = await response.json()
        this.productslist = this.productslist.map(product => (product.id === id ? data : product))

      } catch (error) {
        console.error('Error while editing: ', +error)
      }
    },

    async deleteProduct(id) {
      try {
        await fetch (`http://localhost:8081/Products/Delete/${id}`, {
          method: 'DELETE'
        });
        this.productslist = this.productslist.filter(product => product.id !== id);
      } catch (error) {
        console.error('Error while deleting: ', +error)
      }
    },

    async getProducts() {
      try {
        const response = await fetch(`http://localhost:8081/Products`)
        await response.json().then(data => {
          this.productslist = data
        })
      } catch (error) {
        console.error('Error occured while retrieving products: ' +error);
      }
    },
  },
  mounted() {
    this.getProducts();
  }
}
</script>

<style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
