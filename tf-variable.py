import tensorflow as tf


state = tf.Variable(0, name="counter")

one = tf.constant(1)
new_value = tf.add(state, one)
update = tf.assign(state, new_value)

init_op = tf.initialize_all_variables()

with tf.Session() as session:
	session.run(init_op)
	print 'state =\t', session.run(state)
	
	for _ in range(10):
		#session.run(update)
		print "update:\t", session.run(update)
		print "state:\t", session.run(state)
