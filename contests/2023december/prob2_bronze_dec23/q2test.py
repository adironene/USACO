import os
import subprocess

def run_test(input_file, output_file, solution_script):
    with open(input_file, 'r') as infile:
        input_data = infile.read()
        
    with open(output_file, 'r') as outfile:
        expected_output = outfile.read().strip()
    
    # Run the solution script with the input data
    result = subprocess.run(
        ['python3', solution_script],
        input=input_data.encode(),
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE
    )
    
    # Get the actual output from the script
    actual_output = result.stdout.decode().strip()
    
    # Compare the actual output with the expected output
    if actual_output == expected_output:
        return True
    else:
        return False, actual_output, expected_output

def main():
    solution_script = 'q2.py'
    
    test_number = 1
    while os.path.exists(f'{test_number}.in'):
        input_file = f'{test_number}.in'
        output_file = f'{test_number}.out'
        
        if os.path.exists(output_file):
            test_passed = run_test(input_file, output_file, solution_script)
            if test_passed is True:
                print(f'Test {test_number}: PASSED')
            else:
                _, actual_output, expected_output = test_passed
                print(f'Test {test_number}: FAILED')
                print(f'Expected:\n{expected_output}')
                print(f'Actual:\n{actual_output}')
        else:
            print(f'Output file {output_file} does not exist.')
        
        test_number += 1

if __name__ == '__main__':
    main()
