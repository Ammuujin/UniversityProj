def compute_lps_array(pattern):
    length = 0
    i = 1
    lps = [0] * len(pattern)

    while i < len(pattern):
        if pattern[i] == pattern[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1

    return lps

def kmp_search(text, pattern):
    lps = compute_lps_array(pattern)
    i = j = 0
    found_indices = []

    while i < len(text):
        if pattern[j] == text[i]:
            i += 1
            j += 1

        if j == len(pattern):
            found_indices.append(i - j)
            j = lps[j - 1]

        elif i < len(text) and pattern[j] != text[i]:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1

    return found_indices

# Example usage:
text = "ABABDABACDABABCABAB"
pattern = "ABABCABAB"
indices = kmp_search(text, pattern)
print("Pattern found at indices:", indices)
