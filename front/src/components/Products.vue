<template>
    <div id="products">
        <p v-if="products.length < 1"> No products </p>
        <table v-else style="width: 50%; display: inline-table;" id="customers">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="product in products" v-bind:key="product.id">
                    <td v-if="editing === product.id">
                        <input type="text" v-model="product.name" />
                    </td>
                    <td v-else>{{ product.name }}</td>

                    <td v-if="editing === product.id">
                        <input type="number" v-model="product.price" />
                    </td>
                    <td v-else>{{ product.price }}</td>

                    <td v-if="editing === product.id">
                        <input type="text" v-model="product.description" />
                    </td>
                    <td v-else>{{ product.description }}</td>

                    <td v-if="editing === product.id">
                        <button @click="editProduct(product)" class="btn success">Save</button>
                        <button @click="cancelEdit(product)" class="btn default">Cancel</button>
                    </td>
                    <td v-else>
                        <button @click="editMode(product)" class="btn primary">Edit</button>
                        <button @click="$emit('delete:product', product.id)" class="btn danger">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td v-if="adding === true">
                        <input type="text" v-model="name"/>
                    </td>
                    <td v-if="adding === true">
                        <input type="text" v-model="price"/>
                    </td>
                    <td v-if="adding === true">
                        <input type="text" v-model="description"/>
                    </td>
                    <td v-if="adding === true">
                        <button @click="save()" class="btn success">Save</button>
                        <button @click="cancelSave()" class="btn default">Cancel</button>
                    </td>
                    <td v-else colspan="4">
                        <button @click="addMode()" class="btn primary">Add</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: 'Products-vue',
        props: {
            products: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                editing: null,
                adding:null,
                product : {
                    name: "",
                    price: "",
                    description: ""
                },
                fields: ['first_name', 'last_name', 'age'],
            }
        },
        methods: {
            save() {
                this.product.name = this.name;
                this.product.price = this.price;
                this.product.description= this.description;
                this.$emit('add:product',  this.product)
                this.cachedProduct = Object.assign({}, this.product)
                this.adding = null  
            },

            cancelSave() {
                this.adding = null;
            },

            addMode() {
                this.adding = true
            },

            editMode(product) {
                this.cachedProduct = Object.assign({}, product)
                this.editing = product.id
            },

            cancelEdit(product) {
                Object.assign(product, this.cachedProduct)
                this.editing = null
            },

            editProduct(product){
                if(product.name === '' || product.email === '') return
                this.$emit('edit:product', product.id, product)
                this.editing = null        
            },
        },
    }
</script>

<style>
.btn {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  margin: 4px 2px;
  cursor: pointer;
}

.success {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
}

.primary {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}

.danger {
  background-color: white; 
  color: black; 
  border: 2px solid #f44336;
}

.default {
  background-color: white;
  color: black;
  border: 2px solid #e7e7e7;
}

#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>