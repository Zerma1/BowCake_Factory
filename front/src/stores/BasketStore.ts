//store pinia for basket management with pair of product and quantity
import { defineStore } from 'pinia';
import type Product from '@/services/dtos/product.interface.ts';

export const useBasketStore = defineStore('basket', {
  state: () => ({
    items: [] as { product: Product; quantity: number }[],
  }),
  getters: {
    totalItems: (state) => state.items.reduce((total, item) => total + item.quantity, 0),
    totalPrice: (state) =>
      state.items.reduce((total, item) => total + item.product.unitPriceHT * item.quantity, 0),
    orderProducts: (state) => {
      const products: Record<number, number> = {};
      state.items.forEach((item) => {
        if (item.product.id === undefined) return;
        products[item.product.id] = item.quantity;
      });
      return products;
    },
  },
  actions: {
    addItem(product: Product, quantity: number) {
      const existingItem = this.items.find((item) => item.product.id === product.id);
      if (existingItem) {
        existingItem.quantity += quantity;
      } else {
        this.items.push({ product, quantity });
      }
    },
    removeItem(productId: number | undefined) {
      if (productId === undefined) return;
      this.items = this.items.filter((item) => item.product.id !== productId);
    },
    clearBasket() {
      this.items = [];
    },
  },
});
