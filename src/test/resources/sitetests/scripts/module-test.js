import templatesCollection from "/scripts/TemplatesCollector.js";
import {
 login, logout, whoami,
 getOrder, getOrders,
 postOrder, postOrders,
 putOrder, deleteOrder,
} from "/scripts/fetches.js";

async function test() {
 console.log(templatesCollection.templates)
 console.log(templatesCollection.tools)
 console.log(templatesCollection.languages)
 await login("rickmonald", "guest");
 await logout();
 const identity = await whoami();
 console.log(identity);
 const order = await getOrder(1);
 console.log(JSON.stringify(order));
 const orders = await getOrders();
 console.log(orders);
 const locationSpecific = await postOrder(order);
 console.log(locationSpecific);
 const location = await postOrders(...orders);
 console.log(location);
 order.name = "Purple Rain"
 await putOrder(order)
 console.log(`put ${JSON.stringify(order)}`)
 await deleteOrder(order.id)
 console.log(`deleted ${order.id}`)
}

test();