class ProductAnalyzer:
    def __init__(self, sales_data):
        self.sales_data = sales_data

    def total_sales_per_day(self):
        sales_per_day = {}
        for sale in self.sales_data:
            day = sale['date']
            sales = sale['sales']
            if day in sales_per_day:
                sales_per_day[day] += sales
            else:
                sales_per_day[day] = sales
        return sales_per_day

    def most_sold_product(self):
        product_sales = {}
        for sale in self.sales_data:
            for product, quantity in sale['products'].items():
                if product in product_sales:
                    product_sales[product] += quantity
                else:
                    product_sales[product] = quantity
        most_sold_product = max(product_sales, key=product_sales.get)
        return most_sold_product

    def biggest_spender(self):
        max_spending = 0
        biggest_spender = None
        for sale in self.sales_data:
            customer = sale['customer']
            total_spent = sum(item['price'] * item['quantity'] for item in sale['products'].values())
            if total_spent > max_spending:
                max_spending = total_spent
                biggest_spender = customer
        return biggest_spender

    def products_sold_above_threshold(self, threshold=1):
        products_above_threshold = set()
        for sale in self.sales_data:
            for product, quantity in sale['products'].items():
                if quantity > threshold:
                    products_above_threshold.add(product)
        return list(products_above_threshold)

# Exemplo de dados de vendas
sales_data = [
    {'date': '2024-03-17', 'customer': 'Alice', 'products': {'apple': 2, 'banana': 1, 'orange': 3}, 'sales': 6},
    {'date': '2024-03-17', 'customer': 'Bob', 'products': {'apple': 1, 'banana': 2, 'grape': 2}, 'sales': 5},
    {'date': '2024-03-18', 'customer': 'Alice', 'products': {'apple': 3, 'grape': 2}, 'sales': 5},
    {'date': '2024-03-18', 'customer': 'Charlie', 'products': {'banana': 2, 'orange': 1}, 'sales': 3}
]

# Usando a classe ProductAnalyzer
analyzer = ProductAnalyzer(sales_data)

# Exemplos de uso das funções
print("Total de vendas por dia:", analyzer.total_sales_per_day())
print("Produto mais vendido:", analyzer.most_sold_product())
print("Cliente que mais gastou em uma única compra:", analyzer.biggest_spender())
print("Produtos vendidos em quantidade acima de 1:", analyzer.products_sold_above_threshold())
