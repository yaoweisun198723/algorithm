def overflow_int(num):
    # let num behave like int32 in Java
    MAX_INT = 2147483647
    if not -MAX_INT-1 <= num <= MAX_INT:
        return (num + MAX_INT + 1) % 2 * (MAX_INT + 1) - MAX_INT - 1
    else:
        return num

print overflow_int(2147483648)
