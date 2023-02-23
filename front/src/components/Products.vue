<template>
    <div id="products">
        <p v-if="products.length < 1"> No products </p>
        <table v-else style="width: 50%; display: inline-table;" class="table" border="1" cellspacing="0">
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
                        <button @click="editProduct(product)">Save</button>
                        <button @click="cancelEdit(product)" class="muted-button">Cancel</button>
                    </td>
                    <td v-else>
                        <button @click="editMode(product)">Edit</button>
                        <button @click="$emit('delete:product', product.id)" class="delete-button">Delete</button>
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
                        <button @click="save()">Save</button>
                    </td>
                    <td v-else colspan="4">
                        <button @click="addMode()">Add</button>
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
                }
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